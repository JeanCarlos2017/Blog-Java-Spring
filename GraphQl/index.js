const {ApolloServer} = require("apollo-server");
const PostagemAPI = require('./datasources/postagens'); 
const TemaAPI = require('./datasources/tema')
const token = require('./datasources/token');

const postagemAPI = new PostagemAPI();

const typeDefs= require('./schema')
const resolvers= require('./resolvers');

const dataSources = () => ({
    postagemAPI: new PostagemAPI(),
    temaAPI: new TemaAPI()
});

const server= new ApolloServer({
    typeDefs,
    resolvers, 
    dataSources
})

server
    .listen({port: process.env.PORT || 4000})
    .then(({ url })=>{
        console.log(`graphQL is running at ${url}`);
    })

