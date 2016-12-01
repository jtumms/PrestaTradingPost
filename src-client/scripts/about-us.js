const React = require('react')
const ReactDOM = require('react-dom')
const {NavToHome} = require('./components-shared.js')

const AboutUsView = React.createClass({
  render: function(){
    return (
      <div className="about-us-page-container text-center">
        <NavToHome/>
        <div className="about-header text-center">
          <h1>Presta Trading Post</h1>
        </div>

        <div className="about-us-container">
          <h1><u>About Us</u></h1>
          <p><strong><em>Presta Trading Post</em></strong> is the ultimate meet-up spot for those who have and those who don't.  Have you ever needed a specialty tool to fix something but you did not want to pay for an item that will sit around in your garage forever after you use it once?  Have you ever thought about wanting to try a stand-up paddle board to see if you like it or not and could not see having to pay $800+ just to find out? Well, here at <strong><em>Presta Trading Post</em></strong> we have people who have items just like these that are just sitting around in their garages and who are willing to rent or barter them out.  All you need to do is register with <strong><em>Presta Trading Post</em></strong> today, and then you too can take advantage of these great deals.  Also as a registered member, you will be able to advertise all those items you have sitting around in your garage and begin to rent and barter them out.</p>
        </div>
        <div>
          <a href="https://www.facebook.com/"><i className="fa fa-facebook-official fa-4x multi-icons" aria-hidden="true"></i></a>
          <a href="https://twitter.com/"><i className="fa fa-twitter-square fa-4x multi-icons" aria-hidden="true"></i></a>
          <a href="https://www.instagram.com/?hl=en"><i className="fa fa-instagram fa-4x multi-icons" aria-hidden="true"></i></a>
          <h1 className="copyright multi-icons">&#xa9; <strong><em>2016 Team SilverBack</em></strong></h1>
        </div>
      </div>
    )
  }
});

module.exports = AboutUsView
