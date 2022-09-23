package com.javaeeeee;


import com.javaeeeee.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloDropwizardApplication extends Application<HelloDropwizardConfiguration> {

    private final HibernateBundle<HelloDropwizardConfiguration> hibernate = new HibernateBundle<HelloDropwizardConfiguration>(Student.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(HelloDropwizardConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new HelloDropwizardApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloDropwizardConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }


    @Override
    public void run(HelloDropwizardConfiguration helloDropwizardConfiguration, Environment environment) throws Exception {
        final UserDAO dao = new UserDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UserResource(dao));
    }
}