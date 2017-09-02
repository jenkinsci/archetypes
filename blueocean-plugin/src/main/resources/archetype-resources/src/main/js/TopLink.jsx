import React from 'react';
import { Link } from 'react-router';
import { AppConfig } from '@jenkins-cd/blueocean-core-js';

export default () =>
    <Link className=""
            to={`/organizations/${AppConfig.getOrganizationName()}/executor-info`}
            title="Executor Information">
        Executors
    </Link>
;
