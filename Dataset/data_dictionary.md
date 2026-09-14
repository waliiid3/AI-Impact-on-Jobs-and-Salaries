# Data Dictionary — AI Impact on Jobs & Salaries

| Column | Description |
|---|---|
| `work_year` | Year the salary was reported |
| `experience_level_label` | Decoded experience level (Entry/Mid/Senior/Executive) |
| `employment_type_label` | Decoded employment type (Full-time/Part-time/Contract/Freelance) |
| `job_title` | Original raw job title as self-reported |
| `role_family` | Manually grouped role family derived from job_title (see Step 4 for the exact rules) |
| `salary_in_usd` | Salary converted to USD (primary comparable field) |
| `work_mode` | On-site / Hybrid / Remote, decoded from remote_ratio |
| `company_size` | Company size band as reported (S/M/L) |
| `company_location` | Company's country (ISO code, as provided by source) |
| `salary_outlier_flag` | True if salary_in_usd falls outside the 1.5*IQR range for the full dataset (flagged, not removed) |
| `isco_group_hint` | Approximate ISCO-08 occupational group hint for joining against AI-exposure data (manual mapping, not authoritative) |


## Sources & Licenses
- Salary data: [foorilla/ai-jobs-net-salaries](https://github.com/foorilla/ai-jobs-net-salaries), CC0 1.0.
- AI-exposure scores: Gmyrek, P., Berg, J., Kamiński, K., Konopczyński, F., Ładna, A., Nafradi, B., Rosłaniec, K., Troszyński, M. (2025). *Global Index of Occupational Exposure to Generative AI*, CC BY.

## Known limitations
- Salary data is self-reported (survey), not verified payroll data.
- Country/role coverage is uneven -- some countries and niche titles have very few rows.
- `role_family` and `isco_group_hint` are manual, approximate mappings, documented in full in the companion notebook -- treat as illustrative, not authoritative.
