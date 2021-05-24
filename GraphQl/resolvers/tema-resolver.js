module.exports = {
    postagens(tema, args, { dataSources }) {
        return tema.postagemList;
    }
};