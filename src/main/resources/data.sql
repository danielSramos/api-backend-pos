-- Inserir usuários
INSERT INTO users (id, name, email, created_at) VALUES
    (1, 'John Doe', 'johndoe@example.com', '2025-01-01T10:00:00'),
    (2, 'Jane Doe', 'janedoe@example.com', '2025-01-02T11:30:00'),
    (3, 'Bob Smith', 'bobsmith@example.com', '2025-01-03T12:45:00');

-- Inserir posts
INSERT INTO posts (id, content, title, created_at, user_id) VALUES
    (1, 'This is the content of my first post!', 'My First Post', '2025-01-01T12:00:00', 1),
    (2, 'Let me tell you about my day...', 'A Day in the Life', '2025-01-02T13:00:00', 2),
    (3, 'Here are some tech trends we should be watching...', 'Tech Trends in 2025', '2025-01-03T14:15:00', 3),
    (4, 'Just wanted to share something interesting...', 'Another Post', '2025-01-04T15:00:00', 1);
