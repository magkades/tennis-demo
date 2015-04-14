package org.magkades.tests;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.magkades.dao.MatchDaoMysqlTest;
import org.magkades.hibernate.HibernateUtil;
import org.slf4j.LoggerFactory;

@RunWith( Suite.class )
@Suite.SuiteClasses( {
        MatchDaoMysqlTest.class
} )
public class RuleSuite{

    private static HibernateUtil util;

    // This is a static field.  Per the ClassRule documentation,
    // to use a ClassRule we need a field that is "public, static,
    // and a subtype of of TestRule."
    // See http://junit.czweb.org/apidocs/org/junit/ClassRule.html
    @ClassRule
    public static ExternalResource testRule = new ExternalResource(){
        @Override
        protected void before() throws Throwable{
            LoggerFactory.getLogger(RuleSuite.class).debug("Inside RuleSuite::ExternalResource::before.");
            util = new HibernateUtil();
        }

        @Override
        protected void after(){
            LoggerFactory.getLogger(RuleSuite.class).debug("Inside RuleSuite::ExternalResource::after.");
        }
    };
}