const fs = require('fs').promises;
const path = require('path');

async function getBaseline () {
  const url = 'https://updates.jenkins.io/stable/latestCore.txt'
  console.log(`Getting baseline version from ${url}`)
  const response = await fetch(url, { redirect: 'follow' })
  if (!response.ok) {
    throw new Error(`Failed to fetch latestCore.txt: ${response.status}`)
  }
  return await response.text()
}

async function getBom(major) {
  const bomVersion = `${major}.x`
  const url = `https://repo.jenkins-ci.org/artifactory/api/search/gavc?g=io.jenkins.tools.bom&a=bom-${bomVersion}`
  console.log(`Getting bom version for ${bomVersion} from ${url}`)
  let response = await fetch(url)
  if (!response.ok) {
    throw new Error(`Failed to fetch bom-${bomVersion}: ${response.status}`)
  }
  const json = await response.json()
  const versions = json?.results ?? []
  if (versions.length === 0) {
    throw new Error(`No versions found for bom-${bomVersion}`)
  }
  //https://repo.jenkins-ci.org/artifactory/api/storage/releases/io/jenkins/tools/bom/bom-2.516.x/5165.vdfd66a_57c648/bom-2.516.x-5165.vdfd66a_57c648.pom
  const uri = versions[versions.length - 1].uri
  return uri.split('/').slice(-2,-1)[0]
}

async function findPoms(updater) {
  async function search (currentPath) {
    const files = await fs.readdir(currentPath, { withFileTypes: true })
    for (const file of files) {
      const fullPath = path.join(currentPath, file.name)

      if (file.isDirectory()) {
        if (file.name === 'archetype-resources') {
          const pomPath = path.join(fullPath, 'pom.xml')

          try {
            const fileHandle = await fs.open(pomPath, 'r+')
            try {
              await updater(fileHandle, pomPath)
            } finally {
              await fileHandle.close()
            }
          } catch (err) {
            if (err.code === 'ENOENT') {
              // File doesn't exist - skip silently
              continue
            }
            throw err
          }
        }
        await search(fullPath)
      }
    }
  }
  await search(path.join(__dirname, '..', '..'))
}

function updatePom(major, patch, bom) {
  return async function (fileHandle, pom) {
    const content = await fileHandle.readFile({ encoding: 'utf8' })

    let updatedContent = content.replace(/<jenkins\.baseline>.*<\/jenkins\.baseline>/, `<jenkins.baseline>${major}</jenkins.baseline>`)
    updatedContent = updatedContent.replace(/<jenkins\.version>.*<\/jenkins\.version>/, `<jenkins.version>\${jenkins.baseline}.${patch}</jenkins.version>`)
    updatedContent = updatedContent.replace(/<jenkins\.bom\.version>.*<\/jenkins\.bom\.version>/, `<jenkins.bom.version>${bom}</jenkins.bom.version>`)

    await fileHandle.truncate(0)
    await fileHandle.write(updatedContent, 0)
    console.log(`Updated ${pom}`)
  }
}

async function getCachedBaseline() {
  try {
    return await fs.readFile('.jenkins-baseline-cache', { encoding: 'utf8' })
  } catch (err) {
    // if there is no cache or there is a problem reading the cache,
    // then let's just force an update
    return ''
  }
}

async function cacheBaseline(baseline) {
  try {
    await fs.writeFile('.jenkins-baseline-cache', baseline)
  } catch (err) {
    console.error('Failed to cache baseline', err)
  }
}

async function main() {
  const baseline = await getBaseline() // Assuming always formatted like 2.516.1
  const major = baseline.substring(0, baseline.lastIndexOf('.'))
  const patch  = baseline.substring(baseline.lastIndexOf('.') + 1)
  console.log(`Lastest baseline is ${baseline}`)

  const previousBaseline = await getCachedBaseline();

  if (previousBaseline === baseline) {
    console.log('Baseline is unchanged, exiting')
    return;
  }
  console.log('Baseline has changed, updating')

  const bom = await getBom(major)

  console.log(`New bom version is ${bom}`)

  await findPoms(updatePom(major, patch, bom))

  await cacheBaseline(baseline)
}

main().catch(error => {
  console.error('Error:', error);
  process.exit(1);
});
