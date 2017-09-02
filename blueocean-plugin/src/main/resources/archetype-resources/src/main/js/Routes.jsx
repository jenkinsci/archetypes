import React from 'react';
import { Route } from 'react-router';
import { ExecutorInfoPage } from './ExecutorInfoPage';

export default (
    <Route path="/organizations/:organization/executor-info"
           component={ExecutorInfoPage} />
);
