create table news(
    id serial primary key,
    title varchar(40),
    info TEXT,
    file_hex_id varchar(12),
    created_at TIMESTAMP
);
