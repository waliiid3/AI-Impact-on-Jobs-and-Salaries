# AI Impact on Jobs and Salaries — Hadoop Big Data Analysis

University project for **Big Data Analytics Laboratory (CSE 4346)**, Premier University, Chittagong.

A Big Data project analyzing an AI jobs and salaries dataset using **Hadoop HDFS, Apache Pig, and Java MapReduce**, covering data organization on HDFS, distributed aggregation, and custom reducer logic (priority-queue based top-N selection).

**Submitted by:** Walid Talal (ID: 0222220005101045) — Semester 8th, Batch 42nd, Section A
**Submitted to:** MD Tamim Hossain, Lecturer, Department of Computer Science & Engineering

## Problem Statement

Analyze the AI Jobs and Salaries Big Data dataset using Hadoop (Java MapReduce) and Apache Pig, based on different column fields such as job role, experience level, employment type, work mode and company location, to provide comprehensive insights into how salaries in AI-related roles vary across the workforce.

## Project Requirements Completed

- **5 Apache Pig operations**
- **3 Java MapReduce operations**
- HDFS input/output organization
- Local result organization
- Execution/output screenshot evidence
- Academic project report

## Dataset

`Dataset/ai_jobs_salaries.csv`

- Approximately **71,913 records**
- **17 attributes**
- Main salary comparison field: `salary_in_usd`

Important fields include experience level, employment type, job title, salary, company location, work mode, role family, and salary outlier information. Full field descriptions: `Dataset/data_dictionary.md`.

> Source: [foorilla/ai-jobs-net-salaries](https://huggingface.co/datasets/foorilla/ai-jobs-net-salaries) (CC0 1.0). Salary data is self-reported survey data, not verified payroll data.

## Technologies

- Apache Hadoop
- HDFS
- Hadoop MapReduce
- Apache Pig 0.18.0
- Java / JDK 17
- Windows

## Project Structure

```text
AI-Impact-on-Jobs-and-Salaries/
│
├── Dataset/
│   ├── ai_jobs_salaries.csv
│   └── data_dictionary.md
│
├── HighestSalaryByRole/
│   ├── HighestSalaryByRole.java
│   └── HighestSalaryByRole.jar
│
├── JobsByExperienceLevel/
│   ├── JobsByExperienceLevel.java
│   └── JobsByExperienceLevel.jar
│
├── Top10HighestPaidJobs/
│   ├── Top10HighestPaidJobs.java
│   └── Top10HighestPaidJobs.jar
│
├── PigAnalysis/
│   └── scripts/
│       ├── Top10HighestPayingRoles.pig
│       ├── SalaryByExperienceLevel.pig
│       ├── JobsByWorkMode.pig
│       ├── Top10JobLocations.pig
│       └── SalaryByEmploymentType.pig
│
├── MapReduce Results/
│   └── outputFiles/
│       ├── highestsalarybyrole/
│       ├── jobsbyexperience/
│       └── top10highestpaidjobs/
│
├── Pig Results/
│   ├── Top10HighestPayingRoles/
│   ├── SalaryByExperienceLevel/
│   ├── JobsByWorkMode/
│   ├── Top10JobLocations/
│   └── SalaryByEmploymentType/
│
├── Screenshots/
│   ├── MapReduce/
│   └── Pig/
│
├── README.md
├── SUBMISSION_NOTES.md
└── Walid_Talal_AI_Impact_on_Jobs_and_Salaries_Report.pdf
```

## HDFS Organization

```text
/AI_Impact_Jobs/
├── ai_jobs_salaries.csv
├── pig/
│   ├── top10_highest_paying_roles/
│   ├── salary_by_experience/
│   ├── jobs_by_work_mode/
│   ├── top10_job_locations/
│   └── salary_by_employment_type/
└── mapreduce/
    ├── highest_salary_by_role/
    ├── jobs_by_experience/
    └── top10_highest_paid_jobs/
```

## Apache Pig Analysis

### 1. Top 10 Highest-Paying Job Roles
Groups records by `role_family`, calculates average `salary_in_usd`, sorts descending, and selects the top 10.
Script: `PigAnalysis/scripts/Top10HighestPayingRoles.pig`

### 2. Salary Analysis by Experience Level
Calculates average, maximum, and minimum salary for each experience level.
Script: `PigAnalysis/scripts/SalaryByExperienceLevel.pig`

### 3. Job Distribution by Work Mode
Calculates job count and percentage for on-site, hybrid, and remote work modes.
Script: `PigAnalysis/scripts/JobsByWorkMode.pig`

### 4. Top 10 Job Locations
Counts records by company location, sorts by count, and selects the top 10.
Script: `PigAnalysis/scripts/Top10JobLocations.pig`

### 5. Salary Comparison by Employment Type
Calculates average, maximum, and minimum salary for each employment type.
Script: `PigAnalysis/scripts/SalaryByEmploymentType.pig`

## Java MapReduce Analysis

### 1. Highest Salary by Job Role
`HighestSalaryByRole/HighestSalaryByRole.java` — finds the maximum `salary_in_usd` for each `role_family`.

```bash
hadoop jar HighestSalaryByRole.jar HighestSalaryByRole /AI_Impact_Jobs/ai_jobs_salaries.csv /AI_Impact_Jobs/mapreduce/highest_salary_by_role
```

### 2. Job Count by Experience Level
`JobsByExperienceLevel/JobsByExperienceLevel.java` — counts records for each `experience_level_label`.

```bash
hadoop jar JobsByExperienceLevel.jar JobsByExperienceLevel /AI_Impact_Jobs/ai_jobs_salaries.csv /AI_Impact_Jobs/mapreduce/jobs_by_experience
```

### 3. Top 10 Highest-Paid Individual Job Records
`Top10HighestPaidJobs/Top10HighestPaidJobs.java` — uses a size-10 priority queue to retain the highest-paid individual records.

```bash
hadoop jar Top10HighestPaidJobs.jar Top10HighestPaidJobs /AI_Impact_Jobs/ai_jobs_salaries.csv /AI_Impact_Jobs/mapreduce/top10_highest_paid_jobs
```

## Viewing Results

```bash
hdfs dfs -cat /AI_Impact_Jobs/mapreduce/jobs_by_experience/part-r-00000
```

## Key Results

**Job Count by Experience Level**

```text
Entry-level        7,975
Executive-level     2,841
Mid-level          23,395
Senior-level       37,702
```

**Highest Salary by Role Family**

```text
AI Architect           800000.0
Other / Unclassified   800000.0
Data Engineer          793136.0
Data Analyst           774000.0
Data Scientist         750000.0
ML Engineer            750000.0
Research Scientist     750000.0
Analytics Manager      625000.0
AI Engineer            600000.0
Computer Vision        413160.0
NLP                    275000.0
```

**Top 10 Job Locations by Number of AI Jobs**

```text
US   60147      DE     400
CA    4571      NL     398
GB    2835      LT     312
AU     511      FR     302
ES     223      IN     207
```

## Evidence

Execution and output screenshots are stored under:

```text
Screenshots/MapReduce/
Screenshots/Pig/
```

They are also integrated into the project report.

## Report

`Walid_Talal_AI_Impact_on_Jobs_and_Salaries_Report.pdf` (editable `.docx` also included)

The report documents the methodology, dataset description, all 5 Pig operations, all 3 MapReduce operations, and execution evidence — with a university cover page (course, submission, and supervisor details).

## Notes / Limitations

- Salary records are survey / self-reported data and should not be treated as verified payroll records.
- `role_family` is a derived, manually grouped field based on raw job titles.
- `isco_group_hint` is an approximate ISCO-08 occupational mapping and is not authoritative.
- The Java MapReduce programs use simple comma splitting suited to this specific CSV export.

## Key Insights

- Salary rises consistently with experience level — senior and executive roles earn markedly more.
- AI Architect, Research Scientist and ML Engineer command the highest average pay among role families.
- The large majority of AI jobs in the dataset are on-site rather than remote or hybrid.
- The United States accounts for the largest share of job postings by a wide margin.
