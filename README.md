# AI-Career-Coach

# Step1 :Authenticate your self/ Register Yourself
1. Register a New User : HTTP Method-POST ,Url:/career/register
   
Registers a new user by saving the user details in the database.
eg:
Request Body (JSON)
{
  "username": "john_doe",
  "password": "securePassword123"
}

Success Response: "User registered successfully" along with status code :200

Failure Response: "Please provide Valid Response." along with status code:403

2. Login a User: Method-POST Url-/career/login
Authenticates the user and returns a JWT token if credentials are valid.
eg:
Request Body (JSON)
{
  "username": "john_doe",
  "password": "securePassword123"
}
(JWT token string – should be stored client-side, e.g., in localStorage)
Success Response
"eyJhbGciOiJIUzI1NiJ9..."

Failure Response
"Invalid username or password."

📌 Integration Notes

Use the /career/register endpoint for user sign-up from your frontend (React, Angular, etc.).

After login, store the returned JWT token securely on the client.

Include the token in the Authorization header (Bearer <token>) for subsequent secured API calls.

🔐 Security

AuthenticationManager validates credentials.

JwtService generates JWT tokens for authenticated users.

CrossOrigin(*) allows requests from any frontend origin (can be restricted in production).

▶️ Example (React Axios)
// Register
axios.post("http://localhost:8080/career/register", {
  username: "john_doe",
  password: "securePassword123"
});

// Login
axios.post("http://localhost:8080/career/login", {
  username: "john_doe",
  password: "securePassword123"
}).then(res => {
  localStorage.setItem("token", res.data);
});


# For accessing the main Functionality of the application 
⚙️ Endpoints

1. Submit Initial Quiz

POST /career/initialQuiz/{useCase}

Submits the quiz data along with a specific use case. The backend processes the quiz answers and returns a generated response.

Path Variable

useCase → string representing the scenario or target (e.g., "careerPath", "resumeGuidance")

Request Body (JSON)
{
  "sections": {
    "skills": [
      { "question": "What skills do you have?", "answer": "Java, Spring Boot" }
    ],
    "interests": [
    { "question": "What are your interests?", "answer": "Backend Development" }
    ]
  }
}

Success Response
"Processed quiz response based on use case."

Failure Response
"Please provide Valid Response."

2. Reattempt Quiz

PUT /career/initialQuiz

Marks a quiz reattempt. Returns a confirmation message.

Response
"You have successfully reattempted the Quiz."

3. Get Resume

GET /career/resume

Returns a placeholder resume response (can be expanded later).

Response
"resume"

4. Get Cover Letter

GET /career/coverLetter

Returns a placeholder cover letter response.

Response
"cover letter"

5. Get Industry Connection

GET /career/industryConnection

Fetches industry connection info.

Response
"industry connection"

6. Generate Roadmap

GET /career/roadMap

Generates a career roadmap.

Response
"road map"

📌 Integration Notes

Use /career/initialQuiz/{useCase} for sending quiz data and getting tailored responses.

/career/initialQuiz can be triggered to allow quiz reattempts.

The other GET endpoints (resume, coverLetter, industryConnection, roadMap) are placeholders for resource retrieval.

All endpoints support cross-origin requests (@CrossOrigin("*")).

▶️ Example (React Axios)
// Submit quiz
axios.post("http://localhost:8080/career/initialQuiz/careerPath", {
  sections: {
    skills: [{ question: "What skills?", answer: "Java, Spring Boot" }],
    interests: [{ question: "What interests?", answer: "Backend Development" }]
  }
});

// Reattempt quiz
axios.put("http://localhost:8080/career/initialQuiz");

// Get resume
axios.get("http://localhost:8080/career/resume");



