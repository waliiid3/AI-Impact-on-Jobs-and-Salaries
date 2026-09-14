-- ============================================================
-- PIG OPERATION 4
-- Top 10 Company Locations by Number of AI Jobs
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

grouped_locations = GROUP jobs BY company_location;

location_count = FOREACH grouped_locations GENERATE
    group AS company_location,
    COUNT(jobs) AS job_count;

sorted_locations = ORDER location_count BY job_count DESC;

top_10_locations = LIMIT sorted_locations 10;

STORE top_10_locations
INTO '/AI_Impact_Jobs/pig/top10_job_locations'
USING PigStorage(',');