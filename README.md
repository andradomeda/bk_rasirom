# Rasirom Task Management API

Aplicație RESTful scrisă în Java folosind Spring Boot, care permite gestionarea task-urilor, responsabililor și comentariilor asociate acestora.

##  Funcționalități

- Adăugare, actualizare și listare de **taskuri** și **subtaskuri**
- Adăugare de **comentarii** pe taskuri (doar de către responsabilul atribuit)
- Filtrare taskuri după `dueDate` sau `responsabil`
- Afișare detalii task după ID

---

##  Structură generală

- `model/` – Entitățile JPA (Task, Responsabil, Comment)
- `repository/` – Interfețe pentru operațiuni CRUD
- `controller/` – Endpointuri REST pentru interacțiunea cu aplicația

---

##  Testare API

O colecție Postman este inclusă în livrabil (`rasirom_api.postman_collection.json`) și conține cererile:

- `POST /responsabili` – Adaugă un responsabil
- `POST /tasks` – Adaugă un task
- `POST /tasks/{id}/subtasks` – Adaugă un subtask
- `PATCH /tasks/{id}` – Actualizează un task
- `POST /comments` – Adaugă un comentariu (doar dacă utilizatorul este responsabilul taskului)
- `GET /tasks` – Listează toate taskurile
- `GET /tasks?due_date=today` – Taskuri scadente astăzi
- `GET /tasks?responsabil=1` – Taskuri ale unui responsabil
- `GET /tasks/{id}` – Detalii task

---

## Baza de date

- Schemă: `task`, `responsabil`, `comment`
- Dump-ul SQL (`rasirom_dump.sql`) conține și datele de test necesare

