const {DataSource} = require('apollo-datasource')

class PostagemAPI extends DataSource{
    constructor(config){
        super();
        this.baseURL= `http://localhost:8080/postagem`
    }



    async getAllPostagem(){
        const data = await this.get(this.baseURL);
        return data;
    }

}

module.exports = PostagemAPI;