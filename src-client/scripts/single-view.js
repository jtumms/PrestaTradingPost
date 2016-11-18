const React = require('react')
const ReactDOM = require('react-dom')
const Backbone = require('backbone')



const SingleView = React.createClass({
  // componentDidMount: function(){
  // console.log('hey');
    // ACTIONS.fetchInventoryModel(this.)
  // },
  render:function(){
    let self = this


    return(

      <div className="single-container">
       <div className="row">
         <div className="col-xs-12 col-sm-12 col-md-12">
           <div className="thumbnail thumbnail-container-single text-center">
              <h1>Presta Trading Post</h1>
                <div className="item-card text-center">
                 <img src="http://malakaiboards.com/wp-content/uploads/2016/04/Malakai-106-The-Trip.jpg"></img>
                    <h3>Renter: UserName</h3>
                    <h3>Two LocalMotion Stand-Up Paddlboards</h3>
                    <h3>Asking Price: $20/day for one, $35/both...non-negotiable.</h3>
                    <h3>Misc. Info (*optional)</h3>
                </div>
                   {/* <img src={ }/> */}
           </div>
           {/* <form className="navbar-form inv-form-container" role="search"> */}
              <div className="form-group"></div>
           {/* </form> */}
         </div>
       </div>
     </div>


    )


  }

});

module.exports = SingleView
