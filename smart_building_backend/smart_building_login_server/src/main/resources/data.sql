insert ignore INTO user(name, username, password, email, role, provider) VALUES
('lums', 'lums', '$2a$10$UriWw1AArWKKxR6rmtzHEOWfDiFrdc0YVKyS2EyzpakA7WCTrpbRG', 'lums@super.com', 'ROLE_SUPERUSER', 'local'),
('admin', 'admin', '$2a$10$TIlWVPkn9Kke6Md.hUWsU.TrZtbQERAeAk7b8ndwzcADuKcOa0mD6', 'lums@admin.com', 'ROLE_ADMIN', 'local'),
('user', 'user', '$2a$10$55iN4xP6vFlcpSGGI85giOuN2uCyIftH0pIsRVYP5oN5hAXeILtFC', 'lums@user.com', 'ROLE_USER', 'local');