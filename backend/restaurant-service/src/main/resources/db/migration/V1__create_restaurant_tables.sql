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

    rating NUMERIC(2,1),

    status VARCHAR(30),

    verified BOOLEAN DEFAULT FALSE,

    active BOOLEAN DEFAULT TRUE,

    created_at TIMESTAMP,

    updated_at TIMESTAMP
);