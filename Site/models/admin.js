'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Admin = sequelize.define('Admin', {
        id: {
            field: 'idAdministracao',
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        nome: {
            field: 'nome',
            type: DataTypes.STRING,
            allowNull: false
        },
        login: {
            field: 'email',
            type: DataTypes.STRING,
            allowNull: false
        },
        senha: {
            field: 'senha',
            type: DataTypes.STRING,
            allowNull: false
        }
    }, {
        tableName: 'administracao',
        freezeTableName: true,
        underscored: true,
        timestamps: false,
    });

    return Admin;
};