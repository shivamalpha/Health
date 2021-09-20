# RecruitmentStatics

Service starts at 8085..context path...8085/recruitmentstatics

Sample Curl:

curl --location --request POST 'http://localhost:8085/recruitmentstatics/health/v1/updateCovidResult' \
--header 'Content-Type: application/json' \
--data-raw '{
"user_id":1,
"admin_id":1,
"result":"NEGATIVE"
}'
