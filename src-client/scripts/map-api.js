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

let BubblePin = React.createClass({
  componentWillMount: function(){
    let rangeScope = 10 //  +/- 10  d
    let randomRange = Math.random()*rangeScope*2 - rangeScope // random number btw -4 to 4

    this.setState({styleVals:{
        height: '88px',
        width: '88px',
        borderRadius: '50%',
        background: 'rgba(77, 175, 124, .3)',
        border: 'rgba(77, 175, 124, 1.0) 2px solid',
        transform: `translate(${ randomRange }%, ${ randomRange }%)`
      }
    })
  },


  render: function(){

    return <div style={this.state.styleVals}></div>
  }
})

export default class MapPage extends Component {

    constructor(props) {
        super(props);
        console.log('this props center', this.props.center)
    }

    render() {

       return (

         <GoogleMap
          defaultCenter={ this.props.center   }
          defaultZoom={this.props.zoom}
          bootstrapURLKeys={{
           key: 'AIzaSyBky8AUOXojnW9OVedsmtH2BQgavEkniZM',
           language: 'en'
         }}
          >
            <BubblePin lat={this.props.center.lat} lng={this.props.center.lng} />
         </GoogleMap>
       );
    }
};
module.exports = MapPage
