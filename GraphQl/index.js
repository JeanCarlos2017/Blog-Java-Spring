const {ApolloServer, ApolloError} = require("apollo-server");
const PostagemAPI = require('./datasources/postagens'); 
const TemaAPI = require('./datasources/tema')
const tokenAuthorization = require('./datasources/token');

const postagemAPI = new PostagemAPI();

const typeDefs= require('./schema')
const resolvers= require('./resolvers');

const dataSources = () => ({
    postagemAPI: new PostagemAPI(),
    temaAPI: new TemaAPI(),
   
});

const server= new ApolloServer({
    typeDefs,
    resolvers, 
    dataSources,
    debug: false, 
    formatError: (err) =>{
        if(err.extensions.code == 'INTERNAL_SERVER_ERROR'){
            return new ApolloError("We are having some trouble", "ERROR", {token: "uniquetoken"})
        }
        return err;
    }
   
})

server
    .listen({port: process.env.PORT || 4000})
    .then(({ url })=>{
        console.log(`graphQL is running at ${url}`);
    })

