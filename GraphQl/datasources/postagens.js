const {DataSource} = require('apollo-datasource')
const _ = require('lodash');

class PostagemAPI extends DataSource{
    constructor(config){
        super();
        this.baseURL= `http://localhost:8080/postagem`
    }

    initialize(config){
        
    }


    getAllPostagem(){
        const data = this.get(this.baseURL);
        return data;
    }

    getPostagemById(id){
        console.log(id)
        return null;
    }
}

module.exports = PostagemAPI;