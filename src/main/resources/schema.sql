-- =============================================================
-- schema.sql
-- ComplianceHub API — DDL
-- Mirrors Hibernate entity mappings exactly.
-- Place in src/main/resources/schema.sql and set:
--   spring.sql.init.mode=always
--   spring.jpa.hibernate.ddl-auto=none
-- =============================================================

-- Order matters: referenced tables first, join tables last.
DROP TABLE IF EXISTS regulation_documents;
DROP TABLE IF EXISTS regulation;
DROP TABLE IF EXISTS compliance_document;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_profile;


CREATE TABLE IF NOT EXISTS user_profile
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name       VARCHAR(255),
    last_name        VARCHAR(255),
    email            VARCHAR(255) NOT NULL,
    marketplace      VARCHAR(50),
    -- @Embedded BusinessInfo fields land directly on this table
    business_name    VARCHAR(255),
    business_address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    username          VARCHAR(255) NOT NULL UNIQUE,
    password          VARCHAR(255),
    compliance_status VARCHAR(50),
    user_profile_id   BIGINT,
    CONSTRAINT fk_user_profile FOREIGN KEY (user_profile_id) REFERENCES user_profile (id)
);

CREATE TABLE IF NOT EXISTS role
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_ur_user FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE IF NOT EXISTS compliance_document
(
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_name        VARCHAR(255),
    document_upload_date DATE,
    document_status      VARCHAR(50),
    document_type        VARCHAR(100),
    user_id              BIGINT,
    CONSTRAINT fk_doc_user FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS regulation
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255),
    description TEXT,
    marketplace VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS regulation_documents
(
    regulation_id          BIGINT NOT NULL,
    compliance_document_id BIGINT NOT NULL,
    PRIMARY KEY (regulation_id, compliance_document_id),
    CONSTRAINT fk_rd_regulation FOREIGN KEY (regulation_id) REFERENCES regulation (id),
    CONSTRAINT fk_rd_document FOREIGN KEY (compliance_document_id) REFERENCES compliance_document (id)
);
