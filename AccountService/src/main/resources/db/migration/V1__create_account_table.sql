create table accounts(
    id uuid PRIMARY KEY,
    account_name VARCHAR(255) NOT NULL,
    balance numeric(19, 2),
    user_email VARCHAR(255) not null,
    created_time TIMESTAMP DEFAULT current_timestamp
)