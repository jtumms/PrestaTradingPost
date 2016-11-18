const React = require('react')
const ReactDOM = require('react-dom')
const Backbone = require('backbone')



const SingleView = React.createClass({

  render:function(){
    let self = this

    return(
      <div className="single-item-container text-center">
          <div className="single-home-icon-container">
            <a href=" "><i className="fa fa-home fa-4x single-home-icon" aria-hidden="true"></i></a>
          </div>
          <div className="single-header-container">
                 <h2>LOGO</h2>
                 <h1 className="single-header">Presta Trading Post</h1>
          </div>

        <div className="thumbnail thumbnail-container text-center">
               <img src="http://malakaiboards.com/wp-content/uploads/2016/04/Malakai-106-The-Trip.jpg"></img>
              <div className="caption">
                 <h3>Renter: UserName</h3>
                 <h3>Two LocalMotion Stand-Up Paddlboards</h3>
                 <h3>Asking Price: $20/day for one, $35/both...non-negotiable.</h3>
                 <h3>Misc. Info (*optional)</h3>
              </div>
        </div>
      </div>
    )
  },





});

module.exports = SingleView
