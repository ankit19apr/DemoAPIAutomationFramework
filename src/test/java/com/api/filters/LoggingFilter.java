package com.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.DirectoryStream;

public class LoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification,
                           FilterContext filterContext) {
        //Log Request
        logRequest(filterableRequestSpecification); //intercept
        // Get the Response
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification); // Request is going to be executed.
        //Log Response
        logResponse(response);
        //return the response for further assertions
        return response; // take to the test for assertions
    }

    public void logRequest(FilterableRequestSpecification filterableRequestSpecification) {
        logger.info("Base URI:" + filterableRequestSpecification.getBaseUri());
        logger.info("Request Header:" + filterableRequestSpecification.getHeaders());
        logger.info("Request Payload:" + filterableRequestSpecification.getBody());

    }

    public void logResponse(Response response) {
        logger.info("STATUS CODE:" + response.getStatusCode());
        logger.info("Response Header:" + response.getHeaders());
        logger.info("Response Body:" + response.getBody().prettyPrint());

    }
}
