INSERT INTO profile(id, ,visible, created_date)SELECT 100,1,'test_uz','test_en','test_ru',true,now()WHERE    NOT EXISTS (        SELECT id FROM region WHERE id = 100    );INSERT INTO profile (prt_id, visible, created_date, email, name, password, photo_url, role, status, surname)VALUES (1, true, now(), 'myshukurov@gmail.com', 'MYusuf', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'http://example.com/photo123.jpg', 'ROLE_ADMIN', 'ACTIVE', 'Shukurov');INSERT INTO profile (prt_id, visible, created_date, email, name, password, photo_url, role, status, surname)VALUES (1, true, now(), 'user@gmail.com', 'John', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'http://example.com/photo123.jpg', 'ROLE_USER', 'ACTIVE', 'Doe');INSERT INTO profile (prt_id, visible, created_date, email, name, password, photo_url, role, status, surname)VALUES (1, true, now(), 'admin@gmail.com', 'John', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'http://example.com/photo123.jpg', 'ROLE_USER', 'ACTIVE', 'Doe');INSERT INTO profile (prt_id, visible, created_date, email, name, password, photo_url, role, status, surname)VALUES (1, true, now(), 'public@gmail.com', 'John', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'http://example.com/photo123.jpg', 'ROLE_USER', 'ACTIVE', 'Doe');INSERT INTO profile (prt_id, visible, created_date, email, name, password, photo_url, role, status, surname)VALUES (1, true, now(), 'private@gmail.com', 'John', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'http://example.com/photo123.jpg', 'ROLE_USER', 'ACTIVE', 'Doe');