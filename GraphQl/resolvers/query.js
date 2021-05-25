const { ApolloError } = require('apollo-server')
module.exports = {
        
    postagens: (parent, args, {dataSources}, info) =>{
        return dataSources.postagemAPI.getAllPostagem(args);
    },

    postagemById: (parent, { id }, {dataSources}, info) =>{
        return dataSources.postagemAPI. getPostagemById(id);
    },
    
    temas: (parent, args, {dataSources}, info) =>{
        return dataSources.temaAPI.getTemas(args);
    },

    temaById: (parent, { id }, {dataSources}, info) =>{
        try{
            return dataSources.temaAPI.getTemaById(id);
        }catch(error){
           return new ApolloError("Unable to get this tema", "please check the id", {token: "UNIQUE"})
        }
    },
};