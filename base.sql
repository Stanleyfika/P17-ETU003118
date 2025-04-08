create table Prevision(
    id INT primary key auto_increment,
    libelle varchar(50),
    montant decimal
);
create table Depense(
    id INT primary key auto_increment,
    idPrevision INT,
    somme decimal,
    Foreign Key (idPrevision) references Prevision(id)
);
