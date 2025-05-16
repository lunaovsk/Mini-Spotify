create database db_screen_sound;

CREATE TABLE ss_usuario (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE ss_playlist (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  usuario_id BIGINT,
  CONSTRAINT fk_playlist_usuario 
    FOREIGN KEY (usuario_id) 
    REFERENCES ss_usuario(id)
);

CREATE TABLE ss_midia (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  artista VARCHAR(255) NOT NULL,
  duracao INT NOT NULL,
  genero VARCHAR(50),
  tipo_midia VARCHAR(31) NOT NULL
);

CREATE TABLE ss_playlist_midia (
  playlist_id BIGINT NOT NULL,
  midia_id BIGINT NOT NULL,
  PRIMARY KEY (playlist_id, midia_id),
  CONSTRAINT fk_playlist_midia_playlist 
    FOREIGN KEY (playlist_id) 
    REFERENCES ss_playlist(id) 
    ON DELETE CASCADE,
  CONSTRAINT fk_playlist_midia_midia 
    FOREIGN KEY (midia_id) 
    REFERENCES ss_midia(id) 
    ON DELETE CASCADE
);






