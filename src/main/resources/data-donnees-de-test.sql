INSERT INTO roles (designation)
VALUES ("administrateur"),
       ("ouvrier"),
       ("client");

INSERT INTO user (pseudo, password, role_id)
VALUES ("admin","$2y$10$W5CZjEU8lwt3YSrgCT44pu71GFNNyW.t7N3uFyFK3jD4ufgOHIZiy", 1),
       ("ouvrier", "$2y$10$W5CZjEU8lwt3YSrgCT44pu71GFNNyW.t7N3uFyFK3jD4ufgOHIZiy", 2),
       ("client", "$2y$10$W5CZjEU8lwt3YSrgCT44pu71GFNNyW.t7N3uFyFK3jD4ufgOHIZiy", 3),
       ("Bernard", "$2y$10$W5CZjEU8lwt3YSrgCT44pu71GFNNyW.t7N3uFyFK3jD4ufgOHIZiy", 3),
       ("Sophie", "$2y$10$W5CZjEU8lwt3YSrgCT44pu71GFNNyW.t7N3uFyFK3jD4ufgOHIZiy", 2),
       ("Agathe", "$2y$10$W5CZjEU8lwt3YSrgCT44pu71GFNNyW.t7N3uFyFK3jD4ufgOHIZiy", 3);

INSERT INTO chantier (nom,adresse,client_id,ouvrier_id)
VALUES ("Renovation","18 rue du marabout",4,5),
       ("Construction", "23 avenue du tourniquet", 2, 3);

INSERT INTO tache(temps, nom)
VALUES (5,"balayer"),
       (120,"couler une dalle"),
       (60,"creuser un trou");

INSERT INTO operation(chantier_id, date, ouvrier_id,tache_id,nom)
VALUES (1,current_date,2, 2,"terrasse"),
        (1,current_date,5, 2,"terrasse");
