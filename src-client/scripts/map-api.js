const ReactDOM = require('react-dom')
import React, {Component, PropTypes} from 'react'
import GoogleMap from 'google-map-react'



export default class MapPage extends Component {


    constructor(props) {
        super(props);
        console.log('this props', this.props)
    }

    render() {

       return (

         <GoogleMap
          defaultCenter={this.props.center}
          defaultZoom={this.props.zoom}>

         </GoogleMap>
       );
    }
};

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





















// ///////////////////////
module.exports = MapPage
