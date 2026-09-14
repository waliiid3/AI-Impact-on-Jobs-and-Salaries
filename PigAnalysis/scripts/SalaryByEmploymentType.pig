-- ============================================================
-- PIG OPERATION 5
-- Salary Comparison by Employment Type
-- ============================================================

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

jobs = FILTER jobs BY work_year IS NOT NULL;

grouped_employment = GROUP jobs BY employment_type_label;

salary_comparison = FOREACH grouped_employment GENERATE
    group AS employment_type,
    AVG(jobs.salary_in_usd) AS average_salary,
    MAX(jobs.salary_in_usd) AS maximum_salary,
    MIN(jobs.salary_in_usd) AS minimum_salary;

sorted_employment = ORDER salary_comparison BY average_salary DESC;

STORE sorted_employment
INTO '/AI_Impact_Jobs/pig/salary_by_employment_type'
USING PigStorage(',');