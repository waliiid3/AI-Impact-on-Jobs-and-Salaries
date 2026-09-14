-- ============================================================
-- PIG OPERATION 2
-- Salary Analysis by Experience Level
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

-- Remove CSV header
jobs = FILTER jobs BY work_year IS NOT NULL;

-- Group jobs by experience level
grouped_experience = GROUP jobs BY experience_level_label;

-- Calculate salary statistics
salary_analysis = FOREACH grouped_experience GENERATE
    group AS experience_level,
    AVG(jobs.salary_in_usd) AS average_salary,
    MAX(jobs.salary_in_usd) AS maximum_salary,
    MIN(jobs.salary_in_usd) AS minimum_salary;

-- Sort by average salary from highest to lowest
sorted_experience = ORDER salary_analysis BY average_salary DESC;

-- Store result in HDFS
STORE sorted_experience
INTO '/AI_Impact_Jobs/pig/salary_by_experience'
USING PigStorage(',');