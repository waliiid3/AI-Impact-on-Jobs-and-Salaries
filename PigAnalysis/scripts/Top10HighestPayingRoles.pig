-- ============================================================
-- PIG OPERATION 1
-- Top 10 Highest-Paying Job Role Families
-- ============================================================

-- Load the AI Jobs and Salaries dataset from HDFS
jobs = LOAD '/AI_Impact_Jobs/ai_jobs_salaries.csv'
USING PigStorage(',')
AS (
    work_year:int,
    experience_level:chararray,
    employment_type:chararray,
    job_title:chararray,
    salary:double,
    salary_currency:chararray,
    salary_in_usd:double,
    employee_residence:chararray,
    remote_ratio:int,
    company_location:chararray,
    company_size:chararray,
    experience_level_label:chararray,
    employment_type_label:chararray,
    work_mode:chararray,
    salary_outlier_flag:chararray,
    role_family:chararray,
    isco_group_hint:chararray
);

-- Group all records by role family
grouped_roles = GROUP jobs BY role_family;

-- Calculate the average salary for each role family
average_salary = FOREACH grouped_roles GENERATE
    group AS role_family,
    AVG(jobs.salary_in_usd) AS average_salary;

-- Sort role families by average salary in descending order
sorted_roles = ORDER average_salary BY average_salary DESC;

-- Select the top 10 highest-paying role families
top_10_roles = LIMIT sorted_roles 10;

-- Store the final result in HDFS
STORE top_10_roles
INTO '/AI_Impact_Jobs/pig/top10_highest_paying_roles'
USING PigStorage(',');