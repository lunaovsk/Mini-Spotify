create database db_screen_sound;

use db_screen_sound;

CREATE TABLE ss_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE ss_playlist (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    usuario_id BIGINT,
    CONSTRAINT fk_playlist_usuario FOREIGN KEY (usuario_id) REFERENCES ss_usuario(id)
);




CREATE TABLE ss_midia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    artista VARCHAR(255) NOT NULL,
    duracao INT NOT NULL,
    genero VARCHAR(50) NOT NULL
);

CREATE TABLE ss_playlist_midia (
    playlist_id BIGINT NOT NULL,
    midia_id BIGINT NOT NULL,
    PRIMARY KEY (playlist_id, midia_id),
    FOREIGN KEY (playlist_id) REFERENCES ss_playlist(id) ON DELETE CASCADE,
    FOREIGN KEY (midia_id) REFERENCES ss_midia(id) ON DELETE CASCADE
);

ALTER TABLE ss_playlist
ADD COLUMN usuario_id BIGINT;

ALTER TABLE ss_playlist
ADD CONSTRAINT fk_playlist_usuario FOREIGN KEY (usuario_id) REFERENCES ss_usuario(id);

ALTER TABLE ss_midia
MODIFY COLUMN genero ENUM('ROCK', 'POP', 'MPB', 'JAZZ', 'CLASSICA');

ALTER TABLE ss_playlist
ADD CONSTRAINT fk_playlist_usuario FOREIGN KEY (usuario_id) REFERENCES ss_usuario(id);

ALTER TABLE ss_midia
ADD COLUMN tipo_midia ENUM('MUSICA', 'PODCAST', 'AUDIOBOOK') NOT NULL;

ALTER TABLE ss_midia
MODIFY COLUMN duracao TIME NOT NULL;

ALTER TABLE ss_midia DROP COLUMN tipo;



select * from ss_midia where tipo = ?;







