# AI Career Coach Documentation

**AI Career Coach** is an AI-powered career guidance bot designed to help students and graduates explore career paths, take personality tests, plan roadmaps, and generate resumes. The backend exposes REST APIs that interact with the frontend and provide personalized recommendations based on user data and quiz responses.

---

## 📘 Authentication REST API (JWT Based)

**Base URL:** `http://localhost:8080/career`

---

### 1️⃣ Register a New User

**Endpoint:** `POST /career/register`  

**Request Body (JSON):**
```json
{
  "username": "john_doe",
  "password": "mypassword123"
}
Response (200 OK):

arduino
Copy code
"User registered successfully"
Error Response (400 Bad Request):

arduino
Copy code
"Please provide Valid Response."
💡 Notes:

Username must be unique; duplicate entries may return “User already exists”.

2️⃣ Login and Get JWT Token & userId
Endpoint: POST /career/login

Request Body (JSON):

json
Copy code
{
  "username": "john_doe",
  "password": "mypassword123"
}
Response (200 OK):

json
Copy code
[
  "BearerToken :eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFiaG...",
  "User ID :68d777f0618edd1324eb71ed"
]
Error Response:

json
Copy code
{
  "path": "/career/login",
  "error": "Internal Server Error",
  "message": "Bad credentials",
  "timestamp": "2025-09-29T21:37:06.4767138",
  "status": 500
}
💡 Notes:

Save the JWT token and userId in the frontend (e.g., localStorage, Redux).

Required for all protected endpoints.

🔒 Logout (JWT Auth)
Client-Side Logout:

Remove token and userId from storage.

javascript
Copy code
localStorage.removeItem("jwtToken");
✅ No backend call required.

🔹 Quiz & Personality Test Endpoints
Fetch Quiz Questions
Endpoint: GET /career/quiz?grade=<GRADE>

Query Param: grade → required (SSC, HSC, DIPLOMA, GRADUATE)

Auth: JWT required

Response: Quiz JSON object with questions.

Save Quiz Response
Endpoint: POST /career/quizData

Body: QuizData JSON (questions + answers)

Auth: JWT required

Response: "User Data Recorded Successfully"

Fetch Career Paths
Endpoint: GET /career/paths?quizId=<QUIZ_ID>

Param: userId

Auth: JWT required

Response: List of suggested career paths (AI output)

Example Response:

json
Copy code
[
  {
    "careerPath": "Software Engineering",
    "futureScope": "High demand in IT, roles include software developer, data scientist...",
    "eligibility": "Science stream with Mathematics",
    "lifestyle": "Coding, problem-solving, project work",
    "competitiveExams": "JEE, State Engineering Exams",
    "preparationMethod": "Focus on Maths, Physics, CS; practice coding; 4-year Bachelor's",
    "resourceAvailable": "Coursera, Udemy, Khan Academy",
    "prerequisites": "Logical thinking, problem-solving skills",
    "financialFactors": "₹4-20 Lakh for Bachelor's; scholarships available"
  }
]
Finalize Career Path
Endpoint: POST /career/paths?feedback=<feedback>&quizId=<QUIZ_ID>

Params: feedback → "more" or selected career path, userId

Auth: JWT required

Response:

"more" → returns additional suggestions

Else → saves final career path

2️⃣ Roadmap Endpoints
Generate Roadmap
Endpoint: POST /career/roadmap

Params: userId, timeline (e.g., 3 months, 6 months)

Auth: JWT required

Retrieve Roadmap
Endpoint: GET /career/roadmap

Params: userId

Auth: JWT required

1️⃣ Resume Endpoints
Generate Resume
Endpoint: POST /career/resume

Params: userId, careerPath

Body: Resume Data JSON

Auth: JWT required

Download Resume
Endpoint: GET /career/resume

Params: userId

Auth: JWT required

Response: Byte array → convert to Blob on frontend to download

1️⃣ Industry Connection Endpoint
Endpoint: GET /career/connection

Params: careerPath (e.g., Software Engineer, AI/ML Engineer)

Example Response:

json
Copy code
{
  "careerPath": "Software Engineer",
  "connections": [
    {
      "name": "John Doe",
      "company": "Tech Corp",
      "title": "Senior Developer",
      "linkedinUrl": "https://linkedin.com/in/johndoe"
    }
  ]
}
Get Complete User Details
Endpoint: GET /career/user

Params: userId

Response: Complete user details JSON




# 🐳 Running AI Career Coach with MongoDB in Docker

This guide explains how to run both the AI Career Coach JAR application and MongoDB in Docker containers.

---

## 1️⃣ Build & Run Containers

**Build Docker Image for the App:**
```bash
docker build -t aicareercoach-app .
Run Containers using Docker Compose:

bash
Copy code
docker-compose up --build
2️⃣ Stop Containers
Graceful Stop (Preserve Data):

bash
Copy code
docker-compose down
Stop and Remove Containers & Volumes (Delete Data):

bash
Copy code
docker-compose down -v
Stop without Removing Containers:

bash
Copy code
docker-compose stop
Start Existing Containers:

bash
Copy code
docker-compose start
3️⃣ Access MongoDB Data
Option 1: Use mongosh inside the MongoDB container
bash
Copy code
docker exec -it ai_career_coach mongosh
Inside the MongoDB shell:

js
Copy code
show dbs                 // list databases
use ai_career_coach      // switch to your database
show collections         // list collections
db.users.find()          // view documents in "users" collection
Option 2: Use mongosh from your Host Machine
If mongosh is installed locally:

bash
Copy code
mongosh "mongodb://localhost:27017/ai_career_coach"
Option 3: Use a GUI Client
You can connect any MongoDB GUI tool like MongoDB Compass:
Connection string:

bash
Copy code
mongodb://localhost:27017/ai_career_coach
