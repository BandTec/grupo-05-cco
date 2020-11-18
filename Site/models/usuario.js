'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Usuario = sequelize.define('Usuario', {
        id: {
            field: 'idCliente',
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        nome: {
            field: 'nome',
            type: DataTypes.STRING,
            allowNull: false
        },
        rua: {
            field: 'rua',
            type: DataTypes.STRING,
            allowNull: false
        },
        cidade: {
            field: 'cidade',
            type: DataTypes.STRING,
            allowNull: false
        },
        estado: {
            field: 'estado',
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
        tableName: 'cliente',
        freezeTableName: true,
        underscored: true,
        timestamps: false,
    });

    return Usuario;
};