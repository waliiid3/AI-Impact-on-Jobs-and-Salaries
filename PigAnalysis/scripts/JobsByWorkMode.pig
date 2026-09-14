-- ============================================================
-- PIG OPERATION 3
-- Job Distribution by Work Mode
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

grouped_work_mode = GROUP jobs BY work_mode;

work_mode_count = FOREACH grouped_work_mode GENERATE
    group AS work_mode,
    COUNT(jobs) AS job_count;

total_jobs_group = GROUP jobs ALL;

total_jobs = FOREACH total_jobs_group GENERATE
    COUNT(jobs) AS total_count;

crossed_data = CROSS work_mode_count, total_jobs;

work_mode_percentage = FOREACH crossed_data GENERATE
    work_mode,
    job_count,
    (job_count * 100.0 / total_count) AS percentage;

sorted_work_mode = ORDER work_mode_percentage BY job_count DESC;

STORE sorted_work_mode
INTO '/AI_Impact_Jobs/pig/jobs_by_work_mode'
USING PigStorage(',');