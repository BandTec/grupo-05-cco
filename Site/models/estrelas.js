/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Estrelas = sequelize.define('Estrelas', {
        id: {
            field: 'idAvaliacaoParque',
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        cliente: {
            field: 'fkCliente',
            type: DataTypes.INTEGER,
            allowNull: false
        },
        parque: {
            field: 'fkParque',
            type: DataTypes.INTEGER,
            allowNull: false
        },
        avaliacao: {
            field: 'Avaliacao',
            type: DataTypes.INTEGER,
            allowNull: false
        },
    }, {
        tableName: 'avaliacaoParque',
        freezeTableName: true,
        underscored: true,
        timestamps: false,
    });

    return Estrelas;
};