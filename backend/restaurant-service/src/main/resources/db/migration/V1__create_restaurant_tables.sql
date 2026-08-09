CREATE TABLE restaurants
(
    id UUID PRIMARY KEY,

    restaurant_name VARCHAR(200) NOT NULL,

    owner_name VARCHAR(200) NOT NULL,

    email VARCHAR(100) UNIQUE NOT NULL,

    mobile VARCHAR(15) UNIQUE NOT NULL,

    gst_number VARCHAR(30) UNIQUE,

    fssai_number VARCHAR(50) UNIQUE,

    description TEXT,

    rating NUMERIC(2,1) DEFAULT 0.0,

    status VARCHAR(30) NOT NULL,

    verified BOOLEAN NOT NULL DEFAULT FALSE,

    active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP
);