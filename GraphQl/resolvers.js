module.exports = {
    Query: {
        
        postagens: (parent, args, {dataSources}, info) =>{
            return dataSources.postagemAPI.getAllPostagem(args);
        },

        postagemById: (parent, { id }, {dataSources}, info) =>{
            return dataSources.postagemAPI. getPostagemById(id);
        },
        
        tema(){

        }
    },
};