create database db_screen_sound;
use db_screen_sound;
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


select * from ss_playlist_midia;
select * from ss_playlist;
select * from ss_midia;
select * from ss_usuario;

SELECT u.nome, pl.nome, m.titulo, m.artista, sum(m.duracao), m.tipo_midia, m.genero
FROM ss_usuario u 
LEFT JOIN ss_playlist pl ON pl.usuario_id = u.id
LEFT JOIN ss_playlist_midia pm ON pm.playlist_id = pl.id
LEFT JOIN ss_midia m ON pm.midia_id = m.id
GROUP BY u.nome, pl.nome, m.titulo, m.artista, m.tipo_midia, m.genero;

delete from ss_playlist_midia where midia_id = 15;

DELETE FROM ss_playlist_midia 
WHERE playlist_id = 2 AND midia_id = 15;

SELECT * FROM ss_midia
WHERE id IN (15, 36);

  






