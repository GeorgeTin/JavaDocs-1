'use strict';

hrApp.factory('CommonResourcesFactory', function() {
        var baseUrl = "http://localhost:9090/app/mvc/";
        return {
            findAllDepartmentsUrl: baseUrl + "departments/all",
            findAllEmployeesUrl: baseUrl + "employees/all",
            findAllJobsUrl: baseUrl + "jobs/all",
            findAllLocationsUrl: baseUrl + "locations/all",
            findOneDepartmentUrl: baseUrl + "departments/one",
            findOneEmployeeUrl: baseUrl + "employees/one",
            findOneJobUrl: baseUrl + "jobs/one",
            findOneLocationUrl: baseUrl + "locations/one",
            deleteDepartmentUrl: baseUrl + "departments/delete",
            deleteEmployeeUrl: baseUrl + "employees/delete",
            deleteJobUrl: baseUrl + "jobs/delete",
            deleteLocationUrl: baseUrl + "locations/delete",
            addDepartmentUrl: baseUrl + "departments/save",
            addEmployeeUrl: baseUrl + "employees/save",
            addJobUrl: baseUrl + "jobs/create",
            addLocationUrl: baseUrl + "locations/save",
            editDepartmentUrl: baseUrl + "departments/edit",
            editEmployeeUrl: baseUrl + "employees/edit",
            editJobUrl: baseUrl + "jobs/edit",
            editLocationUrl: baseUrl + "locations/edit"
        };
    }
);