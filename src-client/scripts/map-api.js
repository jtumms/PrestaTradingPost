const ReactDOM = require('react-dom')
import React, {Component, PropTypes} from 'react'
import GoogleMap from 'google-map-react'


let Pin = React.createClass({

   render: function(){

      return (
         <div>
            <i className="fa fa-map-marker fa-3x" aria-hidden="true"></i>
         </div>
      )
   }
})

export default class MapPage extends Component {

  // var cityCircle = new google.maps.Circle({
  //   strokeColor: '#FF0000',
  //   strokeOpacity: 0.8,
  //   strokeWeight: 2,
  //   fillColor: '#FF0000',
  //   fillOpacity: 0.35,
  //   map: map,
  //   center: {this.props.center},
  //   radius: 500
  // }),

    constructor(props) {
        super(props);
        console.log('this props center', this.props.center)
    }

    // let pinsJSX = this.props.pinsData.map(function(obj){
    //      return < lat={obj.latitude} lng={obj.longitude}  />
    // })

    render() {

       return (

         <GoogleMap
          defaultCenter={this.props.center}
          defaultZoom={this.props.zoom}>

         </GoogleMap>
       );
    }
};
module.exports = MapPage
// const location ={
//    lat: 32.784618,
//    lng: -79.940918,
//    zoom: 13
// }
// const marker = {
//       marker: {
//          lat: 32.784618,
//          lng: -79.940918
//       },
//       return <Marker {...marker}/>
// AIzaSyAGoG15YaqO4Hp9Si4jUP2u_61EHK-jw-4
//    },
