const _ = require('lodash');
module.exports = {
    Query: {
        
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
            return dataSources.temaAPI. getTemaById(id);
        },
    },
    Postagem: {
      temas(postagem, args, {dataSources}){
           
           return postagem.tema;
        } 
    }
};