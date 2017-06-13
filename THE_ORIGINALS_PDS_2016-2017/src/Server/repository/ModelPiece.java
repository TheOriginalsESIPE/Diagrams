package Server.repository;


	public class ModelPiece {

		public ModelPiece(){
        }
		    

	    public String insert(String ref_piece_detached, String name, String mark, String model, float answer3bis) {
				 String sql = "INSERT INTO piece_detached(ref_piece_detached, name, mark, model, price) VALUES ('"
                         +ref_piece_detached+"','"+name+"','"+mark+"','"+model+"','"+answer3bis+"')";
				 return sql;

	    }

        public String select(String ref_piece_detached){
            return "SELECT piece_detached.ref_piece_detached, model, mark, price, name FROM piece_detached" +
					" WHERE ref_piece_detached= '" + ref_piece_detached + "';" ;
        }


			 public String delete(String ref_piece_detached) {
				 String sql ="DELETE FROM piece_detached WHERE " +
                         "ref_piece_detached='"+ref_piece_detached+"'";
				 return sql;
			 }
			
			
			 public String update(String ref_piece_detached, float price) {
				 String sql ="UPDATE piece_detached set price = "
                         + price +" WHERE ref_piece_detached='"+ref_piece_detached+"'";
				 return sql;
			 }

    }



