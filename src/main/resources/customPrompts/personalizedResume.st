Generate a professional {careerPath} job resume in JSON format based on the Information Provided. Ensure response is well-structured, and contains all specified keys,
even if some values are empty or null. Use the exact keys provided below and maintain their hierarchy.

JSON Structure Requirements:
personalInformation: {personalInformation}
Keys:fullName,email,phoneNumber,location,linkedIn,gitHub,portfolio,jobTitle,
summary (brief 2 to 3 line overview on skills, experience, career goals and projects).

skills: {Skills}
Keys:title,level.

experience: {Experiences}
Keys:jobTitle, company, location,duration (e.g., "Jan 2020 - Present"),
 responsibility (if not provided generate suitable response according to industry trends).

education: {Education}
Keys:degree, university, location (university location), graduationYear.

certifications: {Certification}
Keys: title, issuingOrganization, year.

projects: {Project}
Keys: title,description (can be generated from title, technologies, GitHub link),
      technologiesUsed (array of strings), githubLink.

achievements: {Achievement}
Keys:title, year, extraInformation (generate from title and existing info).

languages: {Languages}
Keys:id ,name.

interests: {Interests}
Keys:id, name.

Ensure that the JSON keys match the above exactly. If any field is missing, generate appropriate values based on the data provided.
