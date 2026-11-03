CREATE TABLE IF NOT EXISTS post (
                                    id         BIGINT       NOT NULL AUTO_INCREMENT,
                                    title      VARCHAR(200) NOT NULL,
    content    TEXT         NOT NULL,
    writer     VARCHAR(50)  NOT NULL,
    view_count INT          NOT NULL DEFAULT 0,
    notice     BIT(1)       NOT NULL DEFAULT b'0',
    created_at DATETIME(6)  NOT NULL,
    updated_at DATETIME(6)  NOT NULL,
    PRIMARY KEY (id)
    );