# AI Impact on Jobs and Salaries — Hadoop Big Data Analysis

A Big Data Analytics project developed for the **Big Data Analytics Laboratory (CSE 4346)** at **Premier University, Chittagong**.

This project analyzes a large-scale **AI Jobs and Salaries dataset** using the Hadoop ecosystem. The project demonstrates how distributed data processing techniques can be used to organize, process, aggregate, and analyze employment and salary data.

The analysis is implemented using **Hadoop HDFS, Apache Pig, and Java MapReduce**, with multiple operations based on job roles, experience levels, employment types, work modes, company locations, and salary values.

The project includes **5 Apache Pig analyses** and **3 Java MapReduce programs**, along with HDFS organization, generated outputs, execution evidence, and an academic project report.

---

## Project Overview

The rapid growth of Artificial Intelligence has created significant changes in the global employment landscape. New AI-related positions are emerging, existing technical roles are evolving, and compensation varies significantly depending on factors such as experience, job role, employment type, location, and work arrangement.

This project uses Big Data processing techniques to analyze these patterns from a large AI jobs and salaries dataset.

Instead of processing the dataset only with traditional single-machine programming, the project demonstrates how the **Hadoop ecosystem** can be used to perform distributed data processing and aggregation.

The project focuses on answering questions such as:

- Which AI-related job roles have the highest average salaries?
- How does salary vary across different experience levels?
- What is the distribution of AI jobs across on-site, remote, and hybrid work modes?
- Which countries contain the highest number of AI job records?
- How does salary vary between full-time, contract, part-time, and freelance employment?
- What is the highest salary recorded for each AI role family?
- Which individual job records have the highest salaries?

---

## Problem Statement

Analyze the **AI Jobs and Salaries Big Data dataset** using **Hadoop Java MapReduce and Apache Pig**.

The analysis should use different dataset fields such as:

- Job role
- Experience level
- Employment type
- Work mode
- Company location
- Salary
- Role family

The objective is to perform distributed data processing and generate meaningful insights into salary patterns and job distributions within the AI-related workforce.

---

## Project Objectives

The main objectives of this project are:

1. To understand how a large dataset can be stored and organized using **Hadoop HDFS**.
2. To perform distributed data analysis using **Apache Pig**.
3. To implement custom distributed processing using **Java MapReduce**.
4. To perform grouping, counting, sorting, filtering, aggregation, and top-N analysis.
5. To compare salary patterns across experience levels and employment types.
6. To identify the most common locations for AI-related jobs.
7. To identify the highest-paying AI role families.
8. To demonstrate the use of a **priority queue** for efficient top-N selection in MapReduce.
9. To generate and organize Hadoop output for further analysis.
10. To provide execution evidence and document the complete Big Data processing workflow.

---

## Project Requirements Completed

The project fulfills the following Big Data Analytics requirements:

- **5 Apache Pig operations**
- **3 Java MapReduce operations**
- Hadoop HDFS input/output organization
- Distributed data processing
- Grouping and aggregation
- Sorting and filtering
- Maximum and minimum salary analysis
- Top-N analysis
- Priority-queue based reducer logic
- Local organization of generated results
- Execution/output screenshots
- Academic project report

---

# Dataset

The project uses the following Kaggle dataset:

**AI Impact on Jobs & Salaries (2020–2026)**

> **Source:** [Kaggle — AI Impact on Jobs & Salaries (2020–2026)](https://www.kaggle.com/datasets/debayank2024/ai-impact-on-jobs-and-salaries-2020-2026)

The dataset is stored locally in the repository as:

```text
Dataset/ai_jobs_salaries.csv
```

### Dataset Statistics

- Approximately **71,913 records**
- **17 attributes**
- Main salary analysis field: `salary_in_usd`

### Important Dataset Fields

| Field | Description |
|---|---|
| `job_title` | Original job title |
| `role_family` | Grouped AI-related role category |
| `experience_level_label` | Human-readable experience level |
| `employment_type_label` | Employment type |
| `salary_in_usd` | Salary represented in USD |
| `company_location` | Country/location of the company |
| `work_mode_label` | On-site, remote, or hybrid |
| `isco_group_hint` | Approximate occupational classification |
| `salary_outlier` | Salary outlier information |

A complete description of the dataset fields is available in:

```text
Dataset/data_dictionary.md
```

---

# Big Data Processing Architecture

The overall processing workflow can be summarized as:

```text
                    AI Jobs & Salaries Dataset
                              │
                              ▼
                    Dataset/ai_jobs_salaries.csv
                              │
                              ▼
                         Hadoop HDFS
                              │
                 ┌────────────┴────────────┐
                 │                         │
                 ▼                         ▼
           Apache Pig                Java MapReduce
                 │                         │
                 ▼                         ▼
        Aggregation & Analysis      Custom Processing
                 │                         │
                 └────────────┬────────────┘
                              │
                              ▼
                     Generated Results
                              │
                              ▼
                  Analysis & Interpretation
```

The dataset is first stored in **HDFS**, which acts as the distributed storage layer.

Apache Pig is then used for high-level data analysis and aggregation, while Java MapReduce programs provide custom implementations for specific analytical requirements.

---

# Technologies Used

| Technology | Purpose |
|---|---|
| **Apache Hadoop** | Distributed data processing ecosystem |
| **HDFS** | Distributed storage of dataset and results |
| **Hadoop MapReduce** | Distributed custom data processing |
| **Apache Pig 0.18.0** | High-level data analysis and aggregation |
| **Java / JDK 17** | MapReduce implementation |
| **Windows** | Development and execution environment |

---

# Project Structure

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
```

---

# HDFS Organization

The dataset and generated Hadoop results are organized in HDFS as follows:

```text
/AI_Impact_Jobs/
├── ai_jobs_salaries.csv
│
├── pig/
│   ├── top10_highest_paying_roles/
│   ├── salary_by_experience/
│   ├── jobs_by_work_mode/
│   ├── top10_job_locations/
│   └── salary_by_employment_type/
│
└── mapreduce/
    ├── highest_salary_by_role/
    ├── jobs_by_experience/
    └── top10_highest_paid_jobs/
```

This structure separates the original input dataset from the outputs generated by the Pig and MapReduce jobs.

---

# Apache Pig Analysis

Apache Pig is used to perform five analytical operations on the dataset.

The Pig scripts perform operations such as:

- Loading data
- Filtering records
- Grouping records
- Counting records
- Calculating averages
- Calculating maximum and minimum values
- Sorting results
- Limiting results

---

## 1. Top 10 Highest-Paying Job Roles

**Script:**

```text
PigAnalysis/scripts/Top10HighestPayingRoles.pig
```

### Processing

The operation:

1. Loads the dataset.
2. Groups records using `role_family`.
3. Calculates the average `salary_in_usd` for each role family.
4. Sorts the role families by average salary in descending order.
5. Selects the top 10 roles.

### Result

```text
AI Architect           212067.96803652967
Research Scientist     195300.18267882188
ML Engineer            187824.77512355847
Analytics Manager      185237.58585858587
Computer Vision        170379.00714285715
AI Engineer            163843.3399503722
Other / Unclassified   153403.35735076468
Data Scientist         151556.63511640797
Data Engineer          144765.3288711013
NLP                    134649.15789473685
```

### Interpretation

The results show that **AI Architect** has the highest average salary among the analyzed role families, followed by **Research Scientist** and **ML Engineer**.

This demonstrates how grouping and aggregation in Pig can be used to compare compensation across different AI-related career categories.

---

## 2. Salary Analysis by Experience Level

**Script:**

```text
PigAnalysis/scripts/SalaryByExperienceLevel.pig
```

### Processing

The operation groups records by `experience_level_label` and calculates:

- Average salary
- Maximum salary
- Minimum salary

### Result

```text
Executive-level   202080.46920098556   625000.0   15000.0
Senior-level      167807.9580393613    800000.0   15645.0
Mid-level         136267.00995939304   800000.0   15000.0
Entry-level        98017.08025078369    793136.0   15000.0
```

### Interpretation

The average salary increases substantially with experience.

The calculated average salaries show the following progression:

```text
Executive-level → $202,080.47
Senior-level    → $167,807.96
Mid-level       → $136,267.01
Entry-level     →  $98,017.08
```

This indicates a strong relationship between professional experience and average compensation within this dataset.

---

## 3. Job Distribution by Work Mode

**Script:**

```text
PigAnalysis/scripts/JobsByWorkMode.pig
```

### Processing

The operation groups jobs according to `work_mode_label` and calculates:

- Number of jobs
- Percentage of total jobs

### Result

```text
On-site   54081   75.20337073964373
Remote    17505   24.34191314505027
Hybrid      327    0.45471611530599476
```

### Interpretation

The dataset is heavily dominated by **on-site jobs**.

Approximately:

- **75.20%** are on-site
- **24.34%** are remote
- **0.45%** are hybrid

Therefore, within this dataset, on-site employment represents the overwhelming majority of recorded AI-related jobs.

---

## 4. Top 10 Job Locations

**Script:**

```text
PigAnalysis/scripts/Top10JobLocations.pig
```

### Processing

The operation:

1. Groups records by company location.
2. Counts the number of job records for each location.
3. Sorts the results in descending order.
4. Selects the top 10 locations.

### Result

```text
US   60147
CA    4571
GB    2835
AU     511
DE     400
NL     398
LT     312
FR     302
ES     223
IN     207
```

### Interpretation

The **United States** has a significantly larger number of records than any other country in the dataset.

The United States is followed by Canada and the United Kingdom, indicating that these locations contribute a substantial portion of the AI job records represented in the dataset.

---

## 5. Salary Comparison by Employment Type

**Script:**

```text
PigAnalysis/scripts/SalaryByEmploymentType.pig
```

### Processing

The operation groups records by employment type and calculates:

- Average salary
- Maximum salary
- Minimum salary

### Result

```text
Full-time   151785.7837359302   800000.0   15000.0
Contract    104232.66057441253   500000.0   16000.0
Part-time    80330.19373219373   405000.0   15966.0
Freelance    50651.5625          100000.0   20000.0
```

### Interpretation

Full-time positions have the highest average salary among the employment categories analyzed.

The average salary ranking is:

```text
Full-time  → $151,785.78
Contract   → $104,232.66
Part-time  →  $80,330.57
Freelance  →  $50,651.56
```

This indicates that employment type is another factor associated with salary differences within the dataset.

---

# Java MapReduce Analysis

Three custom Java MapReduce programs were developed for additional analysis.

Unlike the high-level Pig operations, these programs demonstrate how distributed processing can be implemented directly using Java MapReduce.

---

## 1. Highest Salary by Job Role

**Source:**

```text
HighestSalaryByRole/HighestSalaryByRole.java
```

### Objective

Find the maximum `salary_in_usd` recorded for each `role_family`.

### Processing Logic

The Mapper extracts the role family and salary.

The Reducer receives all salary values belonging to the same role family and determines the maximum salary.

Conceptually:

```text
Input Records
      ↓
Mapper
      ↓
(role_family, salary)
      ↓
Shuffle & Sort
      ↓
Reducer
      ↓
Maximum salary per role
```

### Execution

```bash
hadoop jar HighestSalaryByRole.jar HighestSalaryByRole /AI_Impact_Jobs/ai_jobs_salaries.csv /AI_Impact_Jobs/mapreduce/highest_salary_by_role
```

---

## 2. Job Count by Experience Level

**Source:**

```text
JobsByExperienceLevel/JobsByExperienceLevel.java
```

### Objective

Count the number of job records for each `experience_level_label`.

### Processing Logic

Each Mapper emits:

```text
(experience_level, 1)
```

The Reducer then sums all values associated with the same experience level.

Conceptually:

```text
Entry-level  → 1
Entry-level  → 1
Senior-level → 1
Senior-level → 1
        ↓
     Shuffle
        ↓
     Reducer
        ↓
Entry-level  → total count
Senior-level → total count
```

### Execution

```bash
hadoop jar JobsByExperienceLevel.jar JobsByExperienceLevel /AI_Impact_Jobs/ai_jobs_salaries.csv /AI_Impact_Jobs/mapreduce/jobs_by_experience
```

---

## 3. Top 10 Highest-Paid Individual Job Records

**Source:**

```text
Top10HighestPaidJobs/Top10HighestPaidJobs.java
```

### Objective

Identify the ten individual job records with the highest salary values.

### Processing Logic

The Reducer uses a **size-10 priority queue** to retain only the highest-paid records while processing the dataset.

This avoids keeping every record in memory.

Conceptually:

```text
All Job Records
      ↓
Mapper
      ↓
Salary Records
      ↓
Reducer
      ↓
Size-10 Priority Queue
      ↓
Top 10 Highest-Paid Records
```

### Execution

```bash
hadoop jar Top10HighestPaidJobs.jar Top10HighestPaidJobs /AI_Impact_Jobs/ai_jobs_salaries.csv /AI_Impact_Jobs/mapreduce/top10_highest_paid_jobs
```

---

# Key MapReduce Results

## Job Count by Experience Level

```text
Entry-level        7,975
Executive-level     2,841
Mid-level          23,395
Senior-level       37,702
```

The dataset contains the largest number of **senior-level** job records, followed by mid-level, entry-level, and executive-level records.

---

## Highest Salary by Role Family

```text
AI Architect           800000.0
Other / Unclassified   800000.0
Data Engineer          793136.0
Data Analyst            774000.0
Data Scientist          750000.0
ML Engineer             750000.0
Research Scientist      750000.0
Analytics Manager       625000.0
AI Engineer             600000.0
Computer Vision         413160.0
NLP                     275000.0
```

The maximum recorded salary reaches **$800,000** for both AI Architect and Other / Unclassified role families.

### Top 10 Highest-Paid Individual Job Records

This analysis identifies the **10 individual job records with the highest recorded salaries** in the dataset.

```text
Rank  Salary (USD)  Job Title                    Experience Level  Location  Work Mode
1     800000.0      Software Engineer            Senior-level      US        On-site
2     800000.0      Architect                    Senior-level      US        On-site
3     800000.0      AI Architect                 Mid-level         CA        Remote
4     793136.0      Data Engineer                Entry-level       AT        On-site
5     774000.0      Data Analyst                 Entry-level       MX        On-site
6     750000.0      Analytics Engineer           Mid-level         US        Remote
7     750000.0      Analytics Engineer           Senior-level      US        On-site
8     750000.0      Research Engineer            Mid-level         US        Remote
9     750000.0      Machine Learning Scientist   Mid-level         US        On-site
10    750000.0      Data Scientist               Senior-level      US        On-site
```

### Interpretation

The highest salary recorded in the dataset is **$800,000**, appearing for three individual job records:

- Software Engineer — Senior-level — US — On-site
- Architect — Senior-level — US — On-site
- AI Architect — Mid-level — Canada — Remote

The remaining records in the top 10 range from **$750,000 to $793,136**.

The results demonstrate how the MapReduce program can efficiently identify the highest-paid individual job records from a large dataset using **top-N processing and priority-queue-based selection**.

---

# Result Comparison

The project uses both **Apache Pig and Java MapReduce**, allowing different analytical approaches to be demonstrated.

| Analysis | Technology | Main Operation |
|---|---|---|
| Top 10 Highest-Paying Roles | Pig | Average salary + sorting |
| Salary by Experience | Pig | Average, maximum, minimum |
| Jobs by Work Mode | Pig | Count + percentage |
| Top 10 Job Locations | Pig | Count + sorting |
| Salary by Employment Type | Pig | Average, maximum, minimum |
| Highest Salary by Role | MapReduce | Maximum aggregation |
| Jobs by Experience Level | MapReduce | Counting |
| Top 10 Highest-Paid Jobs | MapReduce | Priority queue / Top-N |

---

# Viewing Results

Hadoop output can be viewed directly from HDFS using:

```bash
hdfs dfs -cat /AI_Impact_Jobs/mapreduce/jobs_by_experience/part-r-00000
```

Other output directories can be viewed using the same approach.

For example:

```bash
hdfs dfs -cat /AI_Impact_Jobs/mapreduce/highest_salary_by_role/part-r-00000
```

```bash
hdfs dfs -cat /AI_Impact_Jobs/mapreduce/top10_highest_paid_jobs/part-r-00000
```

Pig outputs are stored in their respective HDFS output directories and are also organized locally under:

```text
Pig Results/
```

---

# Evidence

Execution and output evidence is provided through screenshots stored in:

```text
Screenshots/
├── MapReduce/
└── Pig/
```

The screenshots demonstrate:

- Hadoop job execution
- Pig execution
- HDFS commands
- Generated output
- Successful completion of processing tasks

The execution evidence is also incorporated into the academic project report.

---

# Project Report

The project includes a detailed academic report:

```text
AI_Impact_on_Jobs_and_Salaries_Report.pdf
```

An editable version is also included:

```text
AI_Impact_on_Jobs_and_Salaries_Report.docx
```

The report contains:

- Project introduction
- Problem statement
- Dataset description
- Hadoop/HDFS methodology
- Apache Pig operations
- Java MapReduce operations
- Generated results
- Output screenshots
- Analysis and discussion
- Conclusion

---

# Key Findings

Based on the performed analyses, several important patterns can be observed.

### 1. Experience is strongly associated with salary

Executive-level positions have the highest average salary, followed by senior-level, mid-level, and entry-level positions.

```text
Executive → Senior → Mid → Entry
```

The average salary decreases consistently across these experience categories.

---

### 2. AI Architect has the highest average salary

Among the analyzed role families, **AI Architect** has the highest average salary at approximately:

```text
$212,067.97
```

Research Scientist and ML Engineer follow with average salaries of approximately:

```text
Research Scientist → $195,300.18
ML Engineer        → $187,824.78
```

---

### 3. On-site jobs dominate the dataset

Approximately **75.20%** of the job records are categorized as on-site.

Remote jobs account for approximately **24.34%**, while hybrid jobs represent less than **1%**.

---

### 4. The United States dominates job records

The United States has approximately:

```text
60,147 job records
```

This is substantially higher than the next locations:

```text
Canada          4,571
United Kingdom  2,835
Australia         511
```

---

### 5. Full-time positions have the highest average salary

The average salary by employment type shows:

```text
Full-time  → $151,785.78
Contract   → $104,232.66
Part-time  →  $80,330.57
Freelance  →  $50,651.56
```

Within this dataset, full-time positions therefore have the highest average compensation.

---

# Notes and Limitations

The results should be interpreted within the limitations of the dataset and implementation.

- Salary records are survey/self-reported data and should not be treated as verified payroll records.
- The dataset may not represent the complete global AI employment market.
- `role_family` is a derived field created by grouping raw job titles into broader categories.
- `isco_group_hint` represents an approximate occupational mapping and should not be considered an authoritative classification.
- The analysis represents patterns in the available dataset and does not establish causal relationships.
- Salary values may contain extreme values or outliers.
- The Java MapReduce programs use simple comma-based CSV parsing, which is suitable for this specific dataset export but is not a full CSV parser.
- Country codes such as `US`, `CA`, and `GB` represent company locations rather than necessarily the physical location of the employee.

---

# Learning Outcomes

Through this project, the following Big Data concepts were demonstrated:

- Hadoop ecosystem fundamentals
- HDFS data storage
- Distributed data processing
- MapReduce programming model
- Mapper and Reducer implementation
- Shuffle and Sort concept
- Apache Pig Latin
- Filtering and grouping
- Aggregation functions
- Sorting and limiting
- Top-N processing
- Priority queue implementation
- HDFS input/output management
- Big Data result interpretation

---

# Conclusion

This project demonstrates how the Hadoop ecosystem can be used to process and analyze a large AI jobs and salaries dataset.

By combining **HDFS, Apache Pig, and Java MapReduce**, the project performs multiple forms of distributed analysis, including salary aggregation, job counting, location analysis, work-mode distribution, employment-type comparison, maximum salary detection, and top-N selection.

The results indicate that experience level, job role, employment type, and work arrangement are associated with substantial differences in the salary and job distributions represented in the dataset.

The project therefore provides practical experience with the complete Big Data workflow:

```text
Dataset
   ↓
HDFS Storage
   ↓
Distributed Processing
   ↓
Pig / MapReduce
   ↓
Aggregation & Analysis
   ↓
Hadoop Output
   ↓
Result Interpretation
```


# Supervisor

**MD Tamim Hossain**  
Lecturer  
Department of Computer Science & Engineering  
Premier University, Chittagong

---

# Academic Information

**Course:** Big Data Analytics Laboratory  
**Course Code:** CSE 4346  
**Institution:** Premier University, Chittagong

---
