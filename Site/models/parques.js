/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Parques = sequelize.define('Parques', {
        id: {
            field: 'idParqueEventos',
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        nome: {
            field: 'nome',
            type: DataTypes.STRING,
            allowNull: false
        },
        telefone: {
            field: 'telefone',
            type: DataTypes.STRING,
            allowNull: false
        },
        img_parque: {
            field: 'imgParque',
            type: DataTypes.STRING,
            allowNull: false
        },
        localizacao: {
            field: 'localizacao',
            type: DataTypes.STRING,
            allowNull: false
        }
    }, {
        tableName: 'parque',
        freezeTableName: true,
        underscored: true,
        timestamps: false,
    });

    return Parques;
};