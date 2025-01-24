package com.geolocations;

import org.jdbi.v3.core.Jdbi;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;

public class IpGeolocationApplication extends Application<IpGeolocationConfiguration> {
    // Entry point of the application. It creates an instance of
    // IpGeolocationApplication and runs it with the provided arguments.
    public static void main(String[] args) throws Exception {
        new IpGeolocationApplication().run(args);
    }

    // Set the configuration source provider to load configuration from resources
    // (YAML file)
    @Override
    public void initialize(Bootstrap<IpGeolocationConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

    @Override
    public void run(IpGeolocationConfiguration configuration, Environment environment) throws Exception {
        final JdbiFactory factory = new JdbiFactory(); // Creating instance of jdbi factory
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "sqlite");
        final IpGeolocationDao dao = jdbi.onDemand(IpGeolocationDao.class);
        dao.createTable();

        // Execute the migration SQL file to alter database table
        // String migrationSql = new String(
        // Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("migration.sql").toURI())));
        // jdbi.useHandle(handle -> handle.execute(migrationSql));

        environment.jersey().register(new IpGeolocationResource(dao));
    }

    @Override
    public String getName() {
        return "ip-geolocation";
    }

}
