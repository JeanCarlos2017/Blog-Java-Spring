const Query = require('./resolvers/query');
const PostagemResolver= require('./resolvers/postagem-resolver');
const TemaResolver= require('./resolvers/tema-resolver')
const Mutation= require('./resolvers/mutation')


module.exports = {
    Query,
    Postagem: PostagemResolver,
    Tema: TemaResolver,
    Mutation
};