package repository;


	public class ModelPiece {
	
		    private String ref_piece_detached;
		    private String name;
		    private String mark;
		    private String model;
		    private float price;

		public ModelPiece(){
        }
		    

	    public String insert(String ref_piece_detached, String name, String mark, String model, float answer3bis) {
				 String sql = "insert into piece_detached(ref_piece_detached, name, mark, model, price) VALUES ('"
                         +ref_piece_detached+"','"+name+"','"+mark+"','"+model+"','"+answer3bis+"')";
				 return sql;

	    }

        /**
         * @modify by yuxin, this function return a Result Set consisted by 2 DTO
         * @param ref_piece_detached
         * @return
         */
        public String select(String ref_piece_detached){
            return "SELECT piece_detached.ref_piece_detached, model, mark, price, name FROM piece_detached";
        }


			 public String delete(String ref_piece_detached) {
				 String sql ="delete from piece_detached where ref_piece_detached='"+ref_piece_detached+"'";
				 return sql;
			 }
			
			
			 public String update(String ref_piece_detached, String answer1) {
				 String sql ="update piece_detached set price ='"+answer1+"' where ref_piece_detached='"+ref_piece_detached+"'";
				 return sql;
			 }

    }




